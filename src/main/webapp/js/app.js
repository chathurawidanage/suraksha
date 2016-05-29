/**
 * @author Yasiru Kassapa
 */
'use strict';
document.addEventListener('DOMContentLoaded', function () {
    if (Notification.permission !== "granted")
        Notification.requestPermission();
});

angular.module('dropzone', []).directive('dropzone', function () {
    return function (scope, element, attrs) {
        var config, dropzone;

        config = scope[attrs.dropzone];

        // create a Dropzone for the element with the given options
        dropzone = new Dropzone(element[0], config.options);

        // bind the given event handlers
        angular.forEach(config.eventHandlers, function (handler, event) {
            dropzone.on(event, handler);
        });
    };
});

var app = angular.module("myApp", ['ngMaterial', 'ngRoute', 'dropzone']);

//-------------Routes
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: 'templates/empty.html',
            controller: 'home-controller'
        }).when("/pledge", {
        templateUrl: 'templates/pledge.html',
        controller: 'pledgeController'
    }).when("/find", {
        templateUrl: 'templates/imagesearch.html',
        controller: 'imageSearchController'
    }).when("/find-res", {
        templateUrl: 'templates/foundPerson.html',
        controller: 'imageResultController'
    }).when("/camp", {
        templateUrl: 'templates/camp.html',
        controller: 'campController'
    });
}]);
//-------------End of Routes
//-------------Themes
app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('teal')
        .accentPalette('orange');
})
//-------------End of Themes
//-------------Directives
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
//-------------End of Directives

app.controller('ip-mainmenu-controller', sk_mainmenu_controller);
app.controller('home-controller', sk_home_controller);
app.controller('camp-controller', sk_camp_controller);
app.controller('blank-controller', sk_blank_controller);

//main menu controller
function sk_mainmenu_controller($scope, $mdDialog, $location, alertService, $http) {
    $scope.reliefCenterCount = 35;
    $scope.donationCount = 400;
    $scope.locatedPeopleCount = 200;
    $scope.open = function (event) {
        $mdDialog.show($mdDialog.alert().title("Open").textContent("Not implemented").ok("Great!").targetEvent(event));
    }
    $scope.loadView = function (view) {
        console.log("loading view");
        $scope.$parent.open();
        $location.path(view);
    }

    setInterval(function () {
        $http.get("/rest/sms").then(function (data) {
            console.log(data);
            alertService.notify(data.data.alert + "\n" + "Rough Location : " + "6.918,79.863");
        })
    }, 5000);
}

function sk_home_controller($scope, $location) {
    //$location.path("/pledge");
    console.log("home controller");
    $scope.loadView = function (view) {
        console.log("buttons");
        $scope.$parent.open();
        $location.path(view);
    };
}
function sk_blank_controller($scope) {

}
function sk_locator_controller($scope, locator) {
    var image = $scope.image;
    locator.uploadSearchImage(image);
}
function sk_camp_controller($scope, campService) {
    $scope.showPledge = function () {

    }
}

