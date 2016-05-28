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