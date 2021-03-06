(function() {
    'use strict';

    angular
        .module('util.exception')
        .provider('fmuExceptionHandler', exceptionHandlerProvider)
        .config(config);

    function exceptionHandlerProvider() {
        /* jshint validthis:true */
        this.config = {
            appErrorPrefix: undefined
        };

        this.configure = function (appErrorPrefix) {
            this.config.appErrorPrefix = appErrorPrefix;
        };

        this.$get = function() {
            return {config: this.config};
        };
    }
    
    /*@ngInject*/
    function config($provide) {
        $provide.decorator('$exceptionHandler', extendExceptionHandler);
    }
    config.$inject = ['$provide'];

    /*@ngInject*/
    function extendExceptionHandler($delegate, fmuExceptionHandler, logger) {
        return function(exception, cause) {
            var appErrorPrefix = fmuExceptionHandler.config.appErrorPrefix || '';
            var errorData = {exception: exception, cause: cause};
            exception.message = appErrorPrefix + exception.message;
            $delegate(exception, cause);
            /**
                TODO handle errors here.
             */
            logger.error(exception.message, errorData);
        };
    }
    extendExceptionHandler.$inject = ['$delegate', 'fmuExceptionHandler', 'logger'];
})();
