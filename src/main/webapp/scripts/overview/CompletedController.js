'use strict';

angular.module('fmuClientApp')
    .controller('CompletedController', ['$scope', 'AuthService', 'EAVROP_STATUS',
        function ($scope, AuthService, EAVROP_STATUS) {
            $scope.authService = AuthService;
            $scope.dateKey = 'creationTime';
            $scope.startDate = new Date();
            $scope.endDate = new Date();
            $scope.endDate.setMonth($scope.startDate.getMonth() + 1);
            $scope.dateKey = 'dateDelivered';

            $scope.completedStatus = EAVROP_STATUS.completed;
            $scope.headerFields = [
                {
                    key: 'arendeId',
                    name: 'Ärende-ID',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                },
                {
                    key: 'utredningType',
                    name: 'Typ',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                },
                {
                    key: 'dagarFromStartToAccepted',
                    name: 'Antal dgr klar',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                },
                {
                    key: 'totalCompletionDays',
                    name: 'Antal dgr för komplettering',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                },
                {
                    key: 'avikelser',
                    name: 'avikelser',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                },

                {
                    key: 'utredareOrganisation',
                    name: 'Utredare, organisation',
                    restricted: ['ROLE_SAMORDNARE', 'ROLE_UTREDARE']
                },
                {
                    key: 'utredareAnsvarig',
                    name: 'Utredare, ansvarig',
                    restricted: ['ROLE_SAMORDNARE', 'ROLE_UTREDARE']
                },
                {
                    key: 'dateIntygDelivered',
                    name: 'Intyg levererades, datum',
                    restricted: ['ROLE_SAMORDNARE', 'ROLE_UTREDARE']
                },
                {
                    key: 'isCompleted',
                    name: 'Utredning komplett ?',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                },
                {
                    key: 'compensationApprovedStatusAndDate',
                    name: 'Godkänd för ersättning',
                    restricted: ['ROLE_UTREDARE', 'ROLE_SAMORDNARE']
                }
            ];
            $scope.headerGroups = [
                {
                    name: null,
                    colorClass: null,
                    colspan: 10
                }
            ];

            $scope.footerHints = [
                {
                    description: 'Ej godkänd',
                    colorClass: 'bg-danger'
                },
                {
                    description: 'Avikelser finns',
                    colorClass: 'bg-warning'
                },
                {
                    description: 'Utredning är avslutad och godkänd',
                    colorClass: 'bg-success'
                }
            ];

            $scope.dateDescription = 'Datumen utgår från det datum då intyg levererats';
        }
    ]);
