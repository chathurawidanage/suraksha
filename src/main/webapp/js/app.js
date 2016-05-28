/**
 * Created by wik2kassa on 5/27/2016.
 */
'use strict';

var app = angular.module("myApp", ['ngMaterial', 'ngRoute']);

//-------------Routes
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: 'empty.htm',
        controller: 'home-controller'
    }).when("/pledge", {
        templateUrl: 'pledge.htm',
        controller: 'pledge-controller'
    });
}]);
//-------------End of Routes
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
app.controller('pledge-controller', sk_pledge_controller);

//main menu controller
function sk_mainmenu_controller($scope, $mdDialog, $location) {
    $scope.reliefCenterCount = 35;
    $scope.donationCount = 400;
    $scope.locatedPeopleCount = 200;
    $scope.open = function (event) {
        $mdDialog.show($mdDialog.alert().title("Open").textContent("Not implemented").ok("Great!").targetEvent(event));
    }
    $scope.loadView = function (view) {
        $location.path(view);
    }
}

function sk_home_controller($scope, $location) {
    $scope.loadView = function (view) {
        console.log("buttons");
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

function sk_pledge_controller($scope,campService) {
    //campService.all().then(function(data) {
    //    data.forEach(function(camp) {
    //        new google.maps.Marker(
    //            {
    //                position : {
    //                    lat : camp.location.lat, lng : camp.location.lon
    //                },
    //                map : map,
    //                icon : getMarkerIcon(camp)
    //            }
    //        )
    //
    //    });
    //});
    var camps = [];
    var campMarkers = [];
    for(var i = 0;i < 10;i++) {
        camps.push({
            id : Math.floor(Math.random() * 70),
            lat: 6.913255 + Math.random() / 10,
            lng: 79.8643277 + Math.random() / 10,
            name : "Location " + i
        })
    };
    var parent = this;
    camps.forEach(function (location) {
        var marker = new google.maps.Marker(
            new google.maps.Marker(
                {
                    position: {
                        lat: location.lat, lng: location.lng
                    },
                    map: map,
                    icon: getMarkerIcon(location)
                }
            )
        );
        marker.camp = location;
        marker.addListener('click', function() {
            parent.markerClick(marker);
        });
    });
    this.markerClick = function (marker) {
        console.log(marker.camp);
    }
    function getMarkerIcon(percentage) {
        var icon = {
            url: "img/mapmarker.png", // url
            size: new google.maps.Size(50, 50), // size
            origin: new google.maps.Point(0,0), // origin
            anchor: new google.maps.Point(25,25) // anchor
        };
        return icon;
    }



}