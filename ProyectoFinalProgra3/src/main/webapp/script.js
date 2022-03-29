function begin() {
    disableButton1(true)
    const xhr = new XMLHttpRequest();
    xhr.open('get', 'servlet-control?option=1', true)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText)
            listData()
            events(data)
        }
    }
    xhr.send(null)

}

function listData() {
    const xhr2 = new XMLHttpRequest();
    xhr2.open('get', 'servlet-control?option=1', true)
    xhr2.onreadystatechange = () => {
        if (xhr2.readyState === 4 && xhr2.status === 200) {
            const data = JSON.parse(xhr2.response)
            data.sort((a, b) => a.name.localeCompare(b.name)).forEach((participant) => {
                participant.events.forEach((event) => {
                    initialTable(event.discipline, participant.name, participant.id, event.eventPosition, event.disciplineType, event.eventName)
                })
            })

        }
    }
    xhr2.send(null)

}

function reload() {
    location.reload()
}

function reset2() {

    document.getElementById("bodyS").innerHTML = "";
}

function reset3() {

    document.getElementById("bodyS1").innerHTML = "";
}

function initialTable(discipline, name, id, position, disciplineType, event) {

    const row = document.createElement('tr');
    let col = document.createElement('td');

    col.appendChild(document.createTextNode(name))
    row.appendChild(col)

    col = document.createElement('td')
    col.appendChild(document.createTextNode(id))
    row.appendChild(col)

    col = document.createElement('td')
    col.appendChild(document.createTextNode(discipline))
    row.appendChild(col)


    col = document.createElement('td')
    col.appendChild(document.createTextNode(disciplineType))
    row.appendChild(col)


    col = document.createElement('td')
    col.appendChild(document.createTextNode(event))
    row.appendChild(col)

    col = document.createElement('td')
    col.appendChild(document.createTextNode(position))
    row.appendChild(col)

    body1.appendChild(row)
}

document.getElementById("createId").addEventListener("change", () => {
    comprobarExistencia();
})

document.getElementById("createName").addEventListener("change", () => {
    comprobarExistencia()
})


function comprobarExistencia() {
    let status = false;
    let status1 = false;
    let id = document.getElementById("createId").value
    let nombre = document.getElementById("createName").value
    const xhr5 = new XMLHttpRequest();
    xhr5.open("GET", "servlet-control?option=1", true);
    xhr5.onreadystatechange = () => {
        if (xhr5.readyState === 4 && xhr5.status === 200) {
            const data = JSON.parse(xhr5.responseText);
            if (compare(id, data) === false) {
                status = false
                disableButton(status)
            } else if (compare(id, data) === true) {
                status = true
                disableButton(status)
                data.forEach(e => {
                    if (e.name === nombre) {
                        status = false
                        disableButton(status)
                        status1 = true
                    }
                })
                if (status1 === false) {
                    alert("Ese ususario ya existe, debe colocar el mismo nombre")
                }

            }
        }

    };
    xhr5.send(null);
}

function compare(idC, data) {
    let status = false
    data.forEach(e => {
        if (e.id === idC) {
            status = true
        }
    })
    return status
}

function disableButton(estado) {
    const button = document.getElementById("addButton")
    const disci = document.getElementById("createDiscipline")
    const tipodis = document.getElementById("createDisciplineType")
    const event = document.getElementById("createEvent")
    const posi = document.getElementById("createEventPosition")

    switch (estado) {

        case true:

            button.disabled = true
            disci.disabled = true
            tipodis.disabled = true
            event.disabled = true
            posi.disabled = true

            break;
        case false:

            button.disabled = false
            disci.disabled = false
            tipodis.disabled = false
            event.disabled = false
            posi.disabled = false
            break;
    }
}

function disableButton1(estado) {
    const button = document.getElementById("addButton")

    switch (estado) {

        case true:

            button.disabled = true

            break;
        case false:

            button.disabled = false
            break;
    }
}


document.getElementById("addButton").addEventListener("click", () => {

    let name = document.getElementById("createName").value
    let id = document.getElementById("createId").value
    let discipline = document.getElementById("createDiscipline").value
    let disciplineType = document.getElementById("createDisciplineType").value
    let event = document.getElementById("createEvent").value
    let eventPosition = document.getElementById("createEventPosition").value

    if (name === "" || id === "" || discipline === "Seleccione..." || disciplineType === "Seleccione..." || event === "Seleccione..." || eventPosition === "") {
        alert("Rellene todos los espacios")
        disableButton1(true)

    } else {
        disableButton1(false)
        const xhr3 = new XMLHttpRequest();
        xhr3.open("post", 'servlet-control?option=2', true)
        xhr3.onreadystatechange = () => {
            if (xhr3.readyState === 4 && xhr3.status === 200) {
            }
        }
        const data = `name=${name}&id=${id}&discipline=${discipline}&disciplineType=${disciplineType}&event=${event}&eventPosition=${eventPosition}`;
        xhr3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr3.send(data)
        document.getElementById("create").reset();
        document.getElementById("body1").innerHTML = '';
    }
})

document.getElementById("deleteButton").addEventListener("click", () => {

    let id = document.getElementById("deleteId").value
    if (id === "") {
        alert("Rellene el campo correspondiente")
    } else {
        const xhr4 = new XMLHttpRequest();
        xhr4.open("post", `servlet-control?option=3`, true)
        xhr4.onreadystatechange = () => {
            if (xhr4.readyState === 4 && xhr4.status === 200) {
            }
        }
        const data = `id=${id}`;
        xhr4.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr4.send(data)
        document.getElementById("form2").reset();
        document.getElementById("body1").innerHTML="";
    }

})

document.getElementById("searchButton").addEventListener('click', () => {

    const initio = document.getElementById('id').value;

    const xhr5 = new XMLHttpRequest();
    xhr5.open("GET", "servlet-control?option=1", true);

    xhr5.onreadystatechange = () => {
        if (xhr5.readyState === 4 && xhr5.status === 200) {
            const data = JSON.parse(xhr5.responseText);
            let tabla = document.getElementById('tabla');
            let bodyS = document.getElementById("bodyS")

            data.forEach(participant => {

                participant.events.forEach(event => {

                    let fila = document.createElement('tr');
                    let td = document.createElement('td');

                    if (participant.id === initio) {

                        const name = document.getElementById("labelName")
                        const id = document.getElementById("labelId")

                        name.innerText = participant.name
                        id.innerText = participant.id
                        td = document.createElement('td');
                        td.innerText = event.discipline;
                        fila.appendChild(td);


                        td = document.createElement('td');
                        td.innerText = event.disciplineType;
                        fila.appendChild(td);

                        td = document.createElement('td');
                        td.innerText = event.eventName;
                        fila.appendChild(td);

                        td = document.createElement('td');
                        td.innerText = event.eventPosition;
                        fila.appendChild(td);

                    }
                    bodyS.appendChild(fila);
                })
            })

            tabla.appendChild(bodyS);
        }
    }


    xhr5.send(null);

    document.getElementById("form3").reset()
    reset2()
})

function events(data) {
    let events = Array()
    data.forEach(participant => {
        participant.events.forEach(event => {
            if (!competRepeated(event, events)) {
                document.getElementById("events").add(new Option(event.eventName))
                events.push(event)
            }
        })
    })

}

function competRepeated(comp, events) {
    let status = false
    events.forEach(event => {
        if (event.eventName === comp.eventName) {
            status = true
        }
    })
    return status
}

document.getElementById("resultsButton").addEventListener("click", () => {

    const initio = document.getElementById('events').value;
    const xhr7 = new XMLHttpRequest();
    xhr7.open("GET", "servlet-control?option=1", true);

    xhr7.onreadystatechange = () => {
        if (xhr7.readyState === 4 && xhr7.status === 200) {
            const data = JSON.parse(xhr7.responseText);
            let tabla1 = document.getElementById('tabla1');
            let bodyS1 = document.getElementById("bodyS1")

            data.forEach(participant => {

                participant.events.forEach(event => {

                    let fila1 = document.createElement('tr');
                    let td1 = document.createElement('td');

                    if (event.eventName === initio) {

                        td1.innerText = participant.name;
                        fila1.appendChild(td1);

                        td1 = document.createElement('td');
                        td1.innerText = participant.id;
                        fila1.appendChild(td1);

                        td1 = document.createElement('td');
                        td1.innerText = event.discipline;
                        fila1.appendChild(td1);


                        td1 = document.createElement('td');
                        td1.innerText = event.disciplineType;
                        fila1.appendChild(td1);

                        td1 = document.createElement('td');
                        td1.innerText = event.eventName;
                        fila1.appendChild(td1);

                        td1 = document.createElement('td');
                        td1.innerText = event.eventPosition;
                        fila1.appendChild(td1);

                    }
                    bodyS1.appendChild(fila1);

                })
            })

            tabla1.appendChild(bodyS1);
        }
    };
    xhr7.send(null);

    document.getElementById("form4").reset()
    reset3()
})

function SoloNumeros(evt) {
    if (window.event) {
        keynum = evt.keyCode;
    } else {
        keynum = evt.which;
    }

    if ((keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13) {

        return true;
    } else {
        alert("Caracter invalido");
        return false;
    }

}


begin()