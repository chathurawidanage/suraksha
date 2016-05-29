/**
 * Created by chathura on 5/29/16.
 */
app.controller('campController', function ($scope, campService, $location) {
    $scope.$parent.globalCtrl.setNavTitle("Setup a Relief Center");
    var ctrl = this;
    ctrl.location = "";
    ctrl.lat;
    ctrl.lon;
    ctrl.name;

    google.maps.event.addListener(map, "rightclick", function (event) {
        var lat = event.latLng.lat();
        var lng = event.latLng.lng();
        // populate yor box/field with lat, lng
        ctrl.location = lat + "," + lng;
        ctrl.lat = lat;
        ctrl.lon = lng;
    });

    ctrl.saveCamp = function () {
        var campObj = new Object();
        campObj.name = ctrl.name;
        campObj.location = new Object();
        campObj.location.lat = ctrl.lat;
        campObj.location.lon = ctrl.lon;
        campService.create(campObj).then(function (data) {
            $scope.$parent.close();
            $location.path("/");
            var marker = new google.maps.Marker(
                {
                    position: {
                        lat: camp.location.lat, lng: camp.location.lon
                    },
                    map: map,
                    icon: {
                        url: "img/mapmarker.png", // url
                        size: new google.maps.Size(50, 50), // size
                        origin: new google.maps.Point(0, 0), // origin
                        anchor: new google.maps.Point(25, 25) // anchor
                    }
                }
            );
            marker.camp = data.data;
        })
    }
});