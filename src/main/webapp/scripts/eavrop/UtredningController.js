'use strict';

angular.module('fmuClientApp')
    .controller('UtredningController', ['$scope', '$filter', '$stateParams', 'AuthService', 'UtredningService', '$modal', 'UTREDNING', 'currentEavrop',
        function ($scope, $filter, $stateParams, AuthService, UtredningService, $modal, UTREDNING, currentEavrop) {
            $scope.authService = AuthService;
            $scope.currentEavropId = $stateParams.eavropId;

            $scope.headerFields = UtredningService.getTableFields();
            $scope.dateDescription = 'Datumen utgår från det datum då intyg levererats';
            $scope.getTableCellValue = UtredningService.getTableCellValue;

            $scope.openBookingCreationDialog = function () {
                var modal = $modal.open({
                    templateUrl: 'views/templates/laggTillHandelseDialog.html',
                    resolve: {
                        currentEavrop: function () {
                            return currentEavrop;
                        },

                        tableParameters: function () {
                            return $scope.tableParameters;
                        }
                    },
                    controller: function ($scope, currentEavrop, tableParameters) {
                        $scope.handelseDate = new Date();
                        $scope.handelseStartTime = new Date();
                        $scope.handelseStartTime.setMinutes(0);
                        $scope.handelseEndTime = new Date();
                        $scope.handelseEndTime.setMinutes(0);
                        $scope.createBookingErrors = [];
                        $scope.tillaggRadio = {value: false};
                        $scope.tolkRadio = false;

                        $scope.handelseTypes = [
                            {type: UTREDNING.editableEvents.examination, name: 'Besök'},
                            {type: UTREDNING.editableEvents.briefing, name: 'Genomgång med patient'},
                            {type: UTREDNING.editableEvents.internalWork, name: 'Internt arbete'}
                        ];

                        $scope.roles = [
                            {name: 'Läkare'},
                            {name: 'Psykolog'},
                            {name: 'Arbetsterapeut'},
                            {name: 'Sjukgymnast'},
                            {name: 'Utredare'}
                        ];

                        $scope.isAFU = function () {
                             return currentEavrop && currentEavrop.utredningType === 'AFU';
                        };

                        function constructBookingObject() {
                            var retval = {
                                eavropId: currentEavrop.eavropId,
                                bookingType: $scope.choosenHandelseType ? $scope.choosenHandelseType.type : null,
                                bookingDate: $scope.handelseDate.getTime(),
                                bookingStartTime: {
                                    hour: $scope.handelseStartTime.getHours(),
                                    minute: $scope.handelseStartTime.getMinutes()
                                },
                                additionalService: $scope.tillaggRadio.value,
                                bookingEndTime: {
                                    hour: $scope.handelseEndTime.getHours(),
                                    minute: $scope.handelseEndTime.getMinutes()
                                },
                                personName: $scope.personName,
                                personRole: $scope.choosenRole ? $scope.choosenRole.name : null,
                                useInterpreter: $scope.tolkRadio
                            };

                            return retval;
                        }

                        $scope.saveHandelse = function () {
                            var dataPackage = constructBookingObject($scope);
                            var promise = UtredningService.createBooking(dataPackage);
                            promise.then(function () {
                                modal.close();
                                tableParameters.reload();
                            }, function () {
                                $scope.createBookingErrors.push(UTREDNING.errors.cannotCreateBooking);
                            });
                        };

                        $scope.cancel = function () {
                            modal.dismiss('cancel');
                        };
                    }
                });
            };
        }
    ])
;