class Point {
    constructor(x, y, r, inArea){
        this.x = x;
        this.y = y;
        this.r = r;
        this.inArea = inArea;
    }
}

const ERROR_STRING = "Укажите корректное значение";
var selectedXElement = null;
var selectedYElement = null;
var selectedRElement = null;
var plotEvent = null;

function drawPlot() {
    var canvasContext = document.getElementById("plot").getContext("2d");
    canvasContext.clearRect(0, 0, canvasContext.width, canvasContext.height);

    var image = new Image();
    image.src = "resources/area.png";
    image.onload = function () {
        canvasContext.drawImage(image, 0, 0);
        drawPoints();
    }
}

function selectX(element) {
    document.getElementById("x_error").innerHTML = "";
    if(element === null) {
        return;
    }

    if(selectedXElement !== null) {
        selectedXElement.style.color ="";
    }

    if(element !== selectedXElement) {
        selectedXElement = element;
        selectedXElement.style.color ="#00acc1";
        document.getElementById("form:x_param").value = selectedXElement.innerHTML;
    } else {
        selectedXElement = null;
    }
}

function selectY(element) {
    document.getElementById("y_error").innerHTML = "";
    if(element === null) {
        return;
    }

    if(element.value.length !== 0) {
        selectedYElement = element;
        document.getElementById("form:y_param").value = selectedYElement.value;
    } else {
        selectedYElement = null;
    }
}

function selectR(element) {
    document.getElementById("r_error").innerHTML = "";
    if(element === null) {
        return false;
    }

    if(selectedRElement !== null) {
        selectedRElement.style.backgroundColor ="white";
    }

    if(element !== selectedRElement) {
        selectedRElement = element;
        selectedRElement.style.backgroundColor = "#E0E0E0";
        document.getElementById("form:r_param").value = selectedRElement.value;
        return true;
    } else {
        selectedRElement = null;
        return false;
    }
}

function check() {
    if(plotEvent === null) {
        document.getElementById("x_error").innerHTML = selectedXElement === null ? ERROR_STRING + " X" : "";
        document.getElementById("y_error").innerHTML = !checkYValue() ? ERROR_STRING + " Y" : "";
    }
    document.getElementById("r_error").innerHTML = selectedRElement === null ? ERROR_STRING + " R" : "";

    if((document.getElementById("x_error").innerHTML === ""
        && document.getElementById("y_error").innerHTML === "" || plotEvent !== null)
        && document.getElementById("r_error").innerHTML === "") {
        selectX(selectedXElement);
        if(selectedYElement !== null) {
            selectedYElement.value = "";
        }
        plotEvent = null;
        return true;
    } else {
        return false;
    }
}

function checkYValue() {
    const y_max = 3;
    const y_min = -3;

    if(selectedYElement === null) {
        return false;
    }

    var y_value = strip(selectedYElement.value);
    return !isNaN(parseFloat(y_value))
        && +y_value >= +y_min
        && +y_value <= +y_max;
}

function strip(number) {
    return number.replace(/[.]00+/, '.0');
}

function drawPoints() {
    var jsonPoints = JSON.parse(document.getElementById("json_points").value);
    for (let i in jsonPoints) {
        drawPoint(
            new Point(jsonPoints[i].x, jsonPoints[i].y, jsonPoints[i].r, jsonPoints[i].inArea)
        );
    }
}

function drawPoint(point) {
    if (point.x === null || point.y === null || point.r === null || point.inArea === null)
        return;

    var rd = (+point.r) / 163;
    var canvas = document.getElementById("plot");
    var canvasRect = canvas.getBoundingClientRect();

    var xCoord = (point.x / rd) + canvasRect.width / 2;
    var yCoord = canvasRect.height / 2 - (point.y / rd);

    canvas.getContext("2d").fillStyle = point.inArea ? "#00FF00" : "#FF0000";
    canvas.getContext("2d").fillRect(xCoord, yCoord, 3, 3);
}

function ajaxEvent(data) {
    if (data.status === "success") {
        drawPlot();
    }
}

function plotClick(e) {
    if(selectedRElement === null) {
        document.getElementById("r_error").innerHTML = ERROR_STRING + " R";
        return;
    }
    plotEvent = e;
    var valueR = selectedRElement.value;

    var rd = valueR / 163;
    var canvas = document.getElementById("plot");
    var canvasRect = canvas.getBoundingClientRect();

    var x = (e.clientX - canvasRect.left - canvasRect.width / 2) * rd;
    var y = (canvasRect.height / 2 - e.clientY + canvasRect.top) * rd;

    x = x.toFixed(2);
    y = y.toFixed(2);

    document.getElementById("form:x_param").value = x;
    document.getElementById("form:y_param").value = y;
    document.getElementById("form:check_button").click();
}