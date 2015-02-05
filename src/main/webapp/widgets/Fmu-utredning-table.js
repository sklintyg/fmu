'use strict';
angular.module('fmuClientApp')
    .directive('fmuUtredningTable', ['ngTableParams', '$filter', 'UtredningService', '$modal',
        function (ngTableParams, $filter, UtredningService, $modal) {
            return {
                restrict: 'E',
                scope: {
                    tableParams: '=?tableParameters',
                    headerGroups: '=?',
                    headerFields: '=',
                    footerHints: '=?',
                    eavropid: '=',
                    accessDataCallback: '&',
                    rowModifiable: '=?'
                },
                controller: function ($scope) {
                    function createDataPackage(bookingId, newStatus, comment) {
                        return {
                            eavropId: $scope.eavropid,
                            bookingId: bookingId,
                            bookingStatus: newStatus,
                            comment: comment
                        };
                    }

                    $scope.cancelChange = function (rowData) {
                        $scope.toogleEditRow(rowData);
                    };
                    $scope.tableConstants = UtredningService.getTextConstants();
                    // TODO when eavrop status is onhold disable editing functionalities
                    $scope.isEditColumn = function (row) {
                        return (row.tolkStatus || row.handelseStatus);
                    };

                    $scope.toogleEditRow = function (row) {
                        row.selectedTolkStatus = row.tolkStatus.currentStatus;
                        if (row.selectedTolkStatus && row.selectedTolkStatus.requireComment) {
                            row.tolkComment = row.tolkStatus.comment;
                        }

                        row.selectedHandelseStatus = row.handelseStatus.currentStatus;
                        if (row.selectedHandelseStatus && row.selectedHandelseStatus.requireComment) {
                            row.handelseComment = row.handelseStatus.comment;
                        }

                        row.isEditExpanded = !row.isEditExpanded;
                    };

                    $scope.changeTolkStatus = function (rowData) {
                        var confirmModal = $modal.open({
                            templateUrl: 'views/templates/changeBookingConfirmationModal.html',
                            resolve: {
                                parent: function () {
                                    return $scope;
                                }
                            },
                            controller: function ($scope, parent) {
                                $scope.save = function () {
                                    var dataPackage = createDataPackage(rowData.bookingId,
                                        rowData.selectedTolkStatus.name,
                                        rowData.selectedTolkStatus.requireComment ? rowData.tolkComment : null);
                                    var promise = UtredningService.changeTolkBooking(dataPackage);
                                    promise.then(function () {
                                            // Success
                                            confirmModal.close();
                                            parent.toogleEditRow(rowData);
                                            parent.tableParams.reload();
                                        },
                                        function (error) {
                                            $scope.bookingChangeErrors = [error];
                                        });
                                };

                                $scope.cancel = function () {
                                    confirmModal.close();
                                };
                            }
                        });
                    };

                    $scope.changeHandelseStatus = function (rowData) {
                        var confirmModal = $modal.open({
                            templateUrl: 'views/templates/changeBookingConfirmationModal.html',
                            resolve: {
                                parent: function () {
                                    return $scope;
                                }
                            },
                            controller: function ($scope, parent) {
                                $scope.save = function () {
                                    var dataPackage = createDataPackage(rowData.bookingId,
                                        rowData.selectedHandelseStatus.name,
                                        rowData.selectedHandelseStatus.requireComment ? rowData.handelseComment : null);
                                    var promise = UtredningService.changeBooking(dataPackage);
                                    promise.then(function () {
                                            // Success
                                            confirmModal.close();
                                            parent.toogleEditRow(rowData);
                                            parent.tableParams.reload();
                                        },
                                        function (error) {
                                            // Failed
                                            $scope.bookingChangeErrors = [error];
                                        });
                                };

                                $scope.cancel = function () {
                                    confirmModal.close();
                                };

                                $scope.getAdditionalExplaination = function () {
                                    if(!rowData){
                                        return null;
                                    }

                                    return UtredningService.getTextConstants().eventsRequireConfirmation[rowData.selectedHandelseStatus.name];
                                };
                            }
                        });
                    };

                    $scope.sort = function (key) {
                        var params = {};
                        params[key] = $scope.tableParams.isSortBy(key, 'asc') ? 'desc' : 'asc';
                        $scope.tableParams.sorting(params);
                        $scope.currentSortKey = key;
                    };

                    $scope.initTableParameters = function () {
                        if (!$scope.tableParams) {

                            /* jshint -W055 */ // XXX: ngTableParams.
                            $scope.tableParams = new ngTableParams({
                                    page: 1, // show first page
                                    count: 10 // count per page
                                },
                                {
                                    getData: function ($defer, params) {
                                        var promise = UtredningService.getAllEvents($scope.eavropid);

                                        promise.then(function (serverResponse) {
                                            var orderedData = params.sorting() ?
                                                $filter('orderBy')(serverResponse, params.orderBy()) :
                                                serverResponse;

                                            params.total(orderedData.length);
                                            $defer.resolve(orderedData);
                                        });
                                    },
                                    $scope: $scope
                                });
                            /* jshint +W055 */
                        }
                    };
                },
                link: function (scope) {
                    scope.getValue = function (key, row) {
                        return scope.accessDataCallback() ? scope.accessDataCallback()(key, row) : row[key];
                    };

                    scope.initTableParameters();
                },
                templateUrl: 'widgets/fmu-utredning-table.html'
            };
        }]);