'use strict';

angular.module('fmu.eavrop')
    .controller('RequestAssignmentController', RequestAssignmentController);

RequestAssignmentController.$inject = ['$scope', '$modal', '$stateParams', 'Dataservice'];

function RequestAssignmentController($scope, $modal, $stateParams, Dataservice) {

    $scope.openAssignUtredareDialog = function() {
        $modal.open({
            templateUrl: 'views/eavrop/assign-utredare-modal.html',
            size: 'md',
            resolve: {
                vardgivarenheter: Dataservice.getVardgivarenhetByEavropId($stateParams.eavropId)
            },
            controller: function($scope, vardgivarenheter, $modalInstance, Dataservice, $stateParams, $state) {
                $scope.vardgivarenheter = vardgivarenheter;
                $scope.model = {
                    selectedVardgivarenhet: {}
                };

                $scope.close = function() {
                    $modalInstance.dismiss('cancel');
                };

                $scope.assign = function() {
                    var result = Dataservice.assignEavropToVardgivarEnhet($stateParams.eavropId, $scope.model.selectedVardgivarenhet.id);
                    result.then(function() {
                        $state.transitionTo($state.current, $stateParams, {
                            reload: true,
                            inherit: false,
                            notify: true
                        });

                        $modalInstance.close();
                    });
                };
            }
        });
    };
}