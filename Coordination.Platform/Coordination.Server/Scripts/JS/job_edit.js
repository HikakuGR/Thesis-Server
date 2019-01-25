
var map;
var infowindow;
function init() {
    map = new google.maps.Map(document.getElementById('map_canvas'), {
        zoom: 1,
        center: new google.maps.LatLng(0, 0),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    infoWindow = new google.maps.InfoWindow();

    google.maps.event.addListener(map, 'click', clickedAddress);
}

function clickedAddress(event)
{
    document.getElementById('Latitude').value = event.latLng.lat();
    document.getElementById('Longitude').value = event.latLng.lng();
    //findAddress(event.latLng);
}

//function enteredCoordinates()
//{
//    var lat = document.getElementById('latitude').value;
//    var lng = document.getElementById('longitude').value;
//    var point = new google.maps.LatLng(lat, lng);
//    findAddress(point);
//}

//function findAddress(point) {
//    var geocoder = new google.maps.Geocoder();
//    geocoder.geocode({latLng: point}, function(results, status) {
//        if (status == google.maps.GeocoderStatus.OK) {
//            if (results[0]) {
//                infoWindow.setContent(results[0].formatted_address);
//                infoWindow.setPosition(point);
//                infoWindow.open(map);
//            }
//        }
//    });
//}
// Register an event listener to fire once when the page finishes loading.
google.maps.event.addDomListener(window, 'load', init);
