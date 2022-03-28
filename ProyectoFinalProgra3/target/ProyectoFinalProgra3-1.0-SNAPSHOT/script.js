function begin() {

    const xhr = new XMLHttpRequest();
    xhr.open('get', 'servlet-control?option=1', true)
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText)
            listData(data)
        }
    }
    xhr.send(null)

}

function listButton() {

    const xhr2 = new XMLHttpRequest();
    xhr2.open('get', 'servlet-control?option=1', true)
    xhr2.onreadystatechange = () => {
        if (xhr2.readyState === 4 && xhr2.status === 200) {
            const data = JSON.parse(xhr2.response)
            listData(data)

        }
    }
    xhr2.send(null)

}

function reset() {

    const body1 = document.getElementById("body1");
    body1.innerHTML = "";

}

function reset2() {

    const bodyS = document.getElementById("bodyS")

    bodyS.innerHTML = "";
}

function reset3() {

    const bodyS1 = document.getElementById("bodyS1")

    bodyS1.innerHTML = "";
}

function listData(data) {

    data.sort((a, b) => a.name.localeCompare(b.name)).forEach((participant) => {
        participant.events.forEach((event) => {
            initialTable(event.discipline, participant.name, participant.id, event.eventPosition, event.disciplineType, event.eventName)
        })
    })
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

function compare1(nameC, data) {
    let status = false
    data.forEach(e => {
        if (e.name === nameC) {
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
        alert("Rellene todos los espaios")

    } else {
        const xhr3 = new XMLHttpRequest();
        xhr3.open("post", 'servlet-control?option=2', true)
        xhr3.onreadystatechange = () => {
            if (xhr3.readyState === 4 && xhr3.status === 200) {
            }
        }
        const data = `name=${name}&id=${id}&discipline=${discipline}&disciplineType=${disciplineType}&event=${event}&eventPosition=${eventPosition}`;
        xhr3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr3.send(data)
        listButton()
        document.getElementById("create").reset();

    }
}) 

function data() {
    const xhr = new XMLHttpRequest();
    xhr.open("get", 'servlet-control?option=1', true)
            return data = JSON.parse(xhr.responseText);

}

function add(name, id, discipline, disciplineType, event, eventPosition) {
    const xhr9 = new XMLHttpRequest();
    xhr9.open("post", 'servlet-control?option=4', true)
    xhr9.onreadystatechange = () => {
        if (xhr9.readyState === 4 && xhr9.status === 200) {
        }
    }
    const data = `name=${name}&id=${id}&discipline=${discipline}&disciplineType=${disciplineType}&event=${event}&eventPosition=${eventPosition}`;
    xhr9.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr9.send(data)
}

function add1(name, id, discipline, disciplineType, event, eventPosition) {
    const xhr3 = new XMLHttpRequest();
    xhr3.open("post", 'servlet-control?option=2', true)
    xhr3.onreadystatechange = () => {
        if (xhr3.readyState === 4 && xhr3.status === 200) {
        }
    }
    const data = `name=${name}&id=${id}&discipline=${discipline}&disciplineType=${disciplineType}&event=${event}&eventPosition=${eventPosition}`;
    xhr3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr3.send(data)
}

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
        listButton()
        document.getElementById("form2").reset();
    }

})

document.getElementById("searchButton").addEventListener('click', () => {

    const initio = document.getElementById('id').value;

    const xhr5 = new XMLHttpRequest();
    xhr5.open("GET", "servlet-control?option=1", true);

    let cont = 0;

    xhr5.onreadystatechange = () => {
        if (xhr5.readyState === 4 && xhr5.status === 200) {
            const data = JSON.parse(xhr5.responseText);

            let tabla = document.getElementById('tabla');

            data.forEach(participant => {
                let fila = document.createElement('tr');
                let td = document.createElement('td');

                if (participant.id === initio) {

                    participant.events.forEach(event => {
                        const name = document.getElementById("labelName")
                        const id = document.getElementById("labelId")

                        name.innerText = participant.name
                        id.innerText = participant.id

                        cont++;

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
                    })


                }
                bodyS.appendChild(fila);
            })

        }

        tabla.appendChild(bodyS);
    }

    xhr5.send(null);

    document.getElementById("form3").reset()
    reset2()
})

document.getElementById("resultsButton").addEventListener("click", () => {
    const initio = document.getElementById('events').value;


    const xhr7 = new XMLHttpRequest();
    xhr7.open("GET", "servlet-control?option=1", true);

    let cont1 = 0;

    xhr7.onreadystatechange = () => {
        if (xhr7.readyState === 4 && xhr7.status === 200) {
            const data = JSON.parse(xhr7.responseText);
            data.forEach(p =>{
                p.events.forEach(e=>{
                    let tabla = document.getElementById('tabla1');

                    for (let i = 0; i < data.length; ++i) {

                        let fila1 = document.createElement('tr');
                        let td1 = document.createElement('td');

                        if (data[i].event === initio) {

                            cont1++;
                            td1.innerText = p.name;
                            fila1.appendChild(td1);

                            td1 = document.createElement('td');
                            td1.innerText = p.id;
                            fila1.appendChild(td1);

                            td1 = document.createElement('td');
                            td1.innerText = e.discipline;
                            fila1.appendChild(td1);


                            td1 = document.createElement('td');
                            td1.innerText = e.disciplineType;
                            fila1.appendChild(td1);

                            td1 = document.createElement('td');
                            td1.innerText = e.eventName;
                            fila1.appendChild(td1);

                            td1 = document.createElement('td');
                            td1.innerText = e.eventPosition;
                            fila1.appendChild(td1);

                        }
                        bodyS1.appendChild(fila1);

                    }

                    tabla.appendChild(bodyS1);
                })
            })


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