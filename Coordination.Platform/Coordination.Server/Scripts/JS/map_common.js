var markers = new Array();

function createUserMarker(map, title, latitude, longitude,markerID) {
    // creating a marker
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(latitude, longitude),
        map: map,
        title: title,
        label: title
        
    });
    marker["markerID"] = markerID;
    markers.push(marker);    
}

function createJobMarker(map,jobID, title, latitude, longitude, markerID) {
    // creating a marker
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(latitude, longitude),
        map: map,
        title: title,
        label: jobID

    });
    marker["markerID"] = markerID;
    markers.push(marker);
}

///function createJobMarker(map, jobID, title, latitude, longitude) {
//    // creating a marker
//    var marker = new google.maps.Marker({
//        position: new google.maps.LatLng(latitude, longitude),
//        map: map,
//        title: jobID ,
//        label: title

//    });
    
//}

function initMap(map_ID, latitude, longitude) {
    map = new google.maps.Map(document.getElementById(map_ID), {
        zoom: 5,
        center: new google.maps.LatLng(latitude, longitude),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    infoWindow = new google.maps.InfoWindow();
    return map;
}
