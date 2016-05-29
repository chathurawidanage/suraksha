/**
 * Created by wik2kassa on 5/29/2016.
 */
app.controller('pledgeController', function ($scope, $mdDialog, $mdMedia, $location, campService) {
    var camps = [];

    var scp = $scope;

    $scope.$parent.globalCtrl.setNavTitle("Pledge Donation");

    $scope.pledges = [];
    console.log($scope.types);

    var markerClick = function (marker) {
        console.log(marker);
        console.log(marker.camp.name);
        campService.requirements(marker.camp.id).then(function (data) {
            marker.camp.requirements = data;
            $scope.camp = marker.camp;
            $scope.$parent.globalCtrl.setNavTitle("Pledge Donation - " + $scope.camp.name);
        });
        $scope.$parent.globalCtrl.openSlide();
    };
    //load all camps from server
    campService.all().then(function (data) {
        for (i = 0; i < data.length; i++) {
            camp = data[i];
            camps.push(data[i]);
            var marker = new google.maps.Marker(
                {
                    position: {
                        lat: camp.location.lat, lng: camp.location.lon
                    },
                    map: map,
                    icon: getMarkerIcon(camp)
                }
            );
            marker.camp = data[i];
            marker.addListener('click', function () {
                markerClick(this);
            });
            console.log(JSON.stringify(camp) + " loaded from server");
        }
        var criticalCamp = camps[0];
        campService.requirements(criticalCamp.id).then(function (reqs) {
            criticalCamp.requirements = reqs;

            console.log(JSON.stringify(reqs) + " of " + criticalCamp.id + " loaded from server");
            $scope.camp = criticalCamp;
            $scope.$parent.globalCtrl.openSlide();
        });


    });

    //show the sidebar
    function DialogController($scope, $mdDialog) {
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.answer = function (answer) {
            $mdDialog.hide(answer);
        };
    }

    $scope.pledge = function (ev) {
        console.log(JSON.stringify($scope.camp.requirements));
        campService.addRequirement($scope.camp.id, $scope.camp.requirements);
        $mdDialog.show({
                controller: DialogController,
                templateUrl: 'templates/thankyouDialog.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true
            })
            .then(function (answer) {
                $scope.status = 'You said the information was "' + answer + '".';
            }, function () {
                $scope.status = 'You cancelled the dialog.';
            });
        $scope.$parent.globalCtrl.closeSlide();
    };
    console.log("pledge controller running");


    function getMarkerIcon(percentage) {
        var icon = {
            url: "img/mapmarker.png", // url
            size: new google.maps.Size(50, 50), // size
            origin: new google.maps.Point(0, 0), // origin
            anchor: new google.maps.Point(25, 25) // anchor
        };
        return icon;
    }

    function getCritical(camps) {
        needyCamp = camps[0];
        neediness = 0;
        camps.forEach(function (camp) {
            if (neediness < camp.required) {
                needyCamp = camp;
                neediness = required;
            }
        })
        return needyCamp;
    }
})
