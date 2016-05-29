/**
 * Created by chathura on 5/29/16.
 */
app.controller('campController', function ($scope) {
    $scope.$parent.globalCtrl.setNavTitle("Setup a Relief Center");
    var ctrl = this;
    ctrl.location = "";

    google.maps.event.addListener(map, "rightclick", function (event) {
        var lat = event.latLng.lat();
        var lng = event.latLng.lng();
        // populate yor box/field with lat, lng
        ctrl.location = lat + "," + lng;
    });
});