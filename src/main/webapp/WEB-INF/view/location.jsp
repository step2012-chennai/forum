<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&libraries=places&language=en-AU"></script>
    <script>
            var autocomplete = new google.maps.places.Autocomplete($("#address")[0], {});
            google.maps.event.addListener(autocomplete, 'place_changed', function() {
            var place = autocomplete.getPlace();
            console.log(place.address_components);
            });
</script>