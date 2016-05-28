/**
 * Created by wik2kassa on 5/29/2016.
 */
app.controller('pledgeController', function($scope, $rootScope, $location, campService) {
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

    //show the sidebar

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
        var marker = new google.maps.Marker (
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
        $rootScope.$emit('sk_pledge_controller:camp_focus', marker);
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
})
