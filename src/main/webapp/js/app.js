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
//-------------Directives
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
//-------------End of Directives
//-------------Services
app.service('hellofy', function() {
    this.alert = function() {
       console.log("hellofy");
    }
});
app.service('locator', ['$http', function ($http) {
    this.uploadSearchImage = function(image){
        var fd = new FormData();
        fd.append('file', image);
        $http.post('/person/search', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(){
            })
            .error(function(){
            });
    }
}]);
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

function sk_locator_controller($scope, locator) {
    var image = $scope.image;
    locator.uploadSearchImage(image);
}