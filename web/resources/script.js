var selectedXElement = null;
var selectedYElement = null;
var selectedRElement = null;

function drawPlot() {
    var canvasContext = document.getElementById("plot").getContext("2d");
    canvasContext.clearRect(0, 0, canvasContext.width, canvasContext.height);

    var image = new Image();
    image.src = "resources/area.png";
    image.onload = function () {
        canvasContext.drawImage(image, 0, 0);
    }
}

function selectX(element) {
    if(element === null) {
        return;
    }

    if(selectedXElement !== null) {
        selectedXElement.style.color ="";
    }
    selectedXElement = element;
    selectedXElement.style.color ="#00acc1";
    document.getElementById("form:x_param").value = selectedXElement.innerHTML;

}

function selectY(element) {
    if(element === null) {
        return;
    }

    selectedYElement = element;
    document.getElementById("form:y_param").value = selectedYElement.value;
}

function selectR(element) {
    if(element === null) {
        return;
    }

    if(selectedRElement !== null) {
        selectedRElement.style.backgroundColor ="white";
    }
    selectedRElement = element;
    selectedRElement.style.backgroundColor = "#E0E0E0";
    document.getElementById("form:r_param").value = selectedRElement.value;
}