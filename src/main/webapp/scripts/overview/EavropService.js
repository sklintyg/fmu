'use strict';

angular.module('fmuClientApp').factory('EavropService', ['$q', '$http', 'RestUrlBuilderService',
    function ($q, $http, RestUrlBuilderService) {
        return {
            getEavrops: function (startDate, endDate, eavropStatus, currentPageNumber,
                                  currentNrOfElementPerPage, sortKey, sortOrder) {
                return $http.get(RestUrlBuilderService.buildOverViewRestUrl(
                    startDate,
                    endDate,
                    eavropStatus,
                    currentPageNumber,
                    currentNrOfElementPerPage,
                    sortKey,
                    sortOrder)
                ).then(function(data) {
                    // Success
                    return data.data;
                }, function(err) {
                    // Failed to retrieve data
                    return $q.reject(err.data);
                });
            }
        }
    }]);