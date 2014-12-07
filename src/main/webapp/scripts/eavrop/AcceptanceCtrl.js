'use strict';

angular.module('fmuClientApp').controller('AcceptanceCtrl', ['$scope', '$modal', '$stateParams', '$state', 'EavropAccept', 'EavropReject',function($scope, $modal, $stateParams, $state, EavropAccept, EavropReject){
	$scope.acceptDialog = function(){
        var assignModal = $modal.open({
            templateUrl: 'views/eavrop/accept-request-modal.html',
            size: 'md',
            resolve: {
            },
            controller: function ($scope, $modalInstance, $stateParams, $state) {
            	
            	$scope.close = function(){
            		$modalInstance.dismiss('cancel');
            	}
            	
            	$scope.accept = function(){
            		var res = new EavropAccept({eavropId: $stateParams.eavropId});
            		var result = res.$accept();
            		result.then(function(res){
            			$state.transitionTo($state.current, $stateParams, {
            			    reload: true,
            			    inherit: false,
            			    notify: true
            			});
            			
            			$modalInstance.close();
            		});
            	}
            }
        });
	}
	
	$scope.rejectDialog = function(){
        var assignModal = $modal.open({
            templateUrl: 'views/eavrop/reject-request-modal.html',
            size: 'md',
            resolve: {
            },
            controller: function ($scope, $modalInstance, $stateParams, $state) {
            	
            	$scope.close = function(){
            		$modalInstance.dismiss('cancel');
            	}
            	
            	$scope.reject = function(){
            		var res = new EavropReject({eavropId: $stateParams.eavropId});
            		var result = res.$reject();
            		result.then(function(res){
            			$state.transitionTo($state.current, $stateParams, {
            			    reload: true,
            			    inherit: false,
            			    notify: true
            			});
            			
            			$modalInstance.close();
            		});
            	}
            }
        });
	}	
}]);