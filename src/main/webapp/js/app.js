/**
 * Created by wik2kassa on 5/27/2016.
 */
'use strict';

var app = angular.module("myApp", ['ngMaterial','ngMap']);

app.service('hellofy', function() {
    this.alert = function() {
       console.log("hellofy");
    }
});
//main menu controller
function ip_mainmenu_controller($mdDialog,hellofy) {
    this.open = function(event) {
        $mdDialog.show($mdDialog.alert().title("Open").textContent("Not implemented").ok("Great!").targetEvent(event));
        hellofy.alert();
    }
};
app.controller("helloWorld", function($scope, $mdDialog, $mdMedia, hellofy) {
    $scope.message = "Hello World";
    $scope.showAlert = function(event) {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('This is an alert title')
                .textContent('You can specify some description text in here.')
                .ariaLabel('Alert Dialog Demo')
                .ok('Got it!')
                .targetEvent(event)
        );

    };


});

app.controller('ip-mainmenu-controller', ip_mainmenu_controller);

// Lasantha test

app.controller('globalController', function($scope, $timeout, $mdSidenav, $log){
    $scope.toggleLeft = buildToggler('left');

    $scope.isOpenLeft = function(){
        return $mdSidenav('left').isOpen();
    };

    function buildToggler(navID) {
        return function() {
            $mdSidenav(navID)
                .toggle()
                .then(function () {
                    $log.debug("toggle " + navID + " is done");
                });
        }
    }

    /**
     * Supplies a function that will continue to operate until the
     * time is up.
     */
    function debounce(func, wait, context) {
        var timer;
        return function debounced() {
            var context = $scope,
                args = Array.prototype.slice.call(arguments);
            $timeout.cancel(timer);
            timer = $timeout(function() {
                timer = undefined;
                func.apply(context, args);
            }, wait || 10);
        };
    }
});

app.controller('RightCtrl', function ($scope, $timeout, $mdSidenav, $log) {
    $scope.close = function () {
        $mdSidenav('left').close()
            .then(function () {
                $log.debug("close RIGHT is done");
            });
    };
});