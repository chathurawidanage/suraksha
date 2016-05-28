/**
 * Created by wik2kassa on 5/27/2016.
 */
'use strict';

var app = angular.module("myApp", ['ngMaterial','ngMap', 'ngRoute']);

//-------------Routes
app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when("/", {
        templateUrl : 'home.htm',
        controller : 'home-controller'
    });
}]);
//-------------End of Routes

//-------------Services
app.service('hellofy', function() {
    this.alert = function() {
       console.log("hellofy");
    }
});
app.service('locator', function($http) {
    this.sendSearchImage = function(image) {

    }
})
//-------------End of Services
app.controller('ip-mainmenu-controller', sk_mainmenu_controller);
app.controller('home-controller',sk_home_controller);

//main menu controller
function sk_mainmenu_controller($scope, $mdDialog, hellofy) {
    $scope.reliefCenterCount = 35;
    $scope.donationCount = 400;
    $scope.locatedPeopleCount = 200;
    this.open = function(event) {
        $mdDialog.show($mdDialog.alert().title("Open").textContent("Not implemented").ok("Great!").targetEvent(event));
        hellofy.alert();
    }
}

function sk_home_controller($scope) {

}