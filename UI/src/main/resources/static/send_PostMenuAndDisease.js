window.onload = function () {
    let button = document.querySelector('button');
}

function addMenuField() {
    // Create new menu and quantity input fields
    const menuField = document.createElement('input');
    menuField.type = 'text';
    menuField.placeholder = '음식 이름';
    menuField.required = true;

    const quantityField = document.createElement('input');
    quantityField.type = 'number';
    quantityField.placeholder = '섭취량(g)';
    quantityField.required = true;

    const button = document.querySelector('button');

    // Append the new fields to the form
    button.insertAdjacentElement('afterend', menuField);
    button.insertAdjacentElement('afterend', quantityField);
    button.insertAdjacentHTML('afterend', '<br>');
}

function sendPostMenuData() {
    // Collect order data from all menu and quantity fields
    const menuFields = document.querySelectorAll('input[type="text"][placeholder="음식 이름"]');
    const menuAmounts = document.querySelectorAll('input[type="number"][placeholder="섭취량(g)"]');

    const menuList = [];

    menuFields.forEach((menuField, index) => {
        const menuName = menuField.value;
        const menuAmount = parseInt(menuAmounts[index].value);
        menuList.push({ menuName, menuAmount });
    });

    // Create an object representing the order
    const orderData = {
        menuDate: document.getElementById('menuDate').value,
        menuTime: document.getElementById('menuTime').value,
        remark: document.getElementById('remark').value,

        loginToken: parseInt(document.getElementsByClassName('loginToken').value),
        menuList: menuList
    };

    // Send the data to the server using fetch
    fetch('/new-post/menu', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData),
    })
        .then(response => {
            if(response.status === 200) {
                alert('주문이 성공적으로 완료되었습니다.');
                response.json().then(data => {
                    console.log(data);
                    window.location.href = '/get-post/'+data.postDate;
                });
            } else {
                // Order was not created
                alert('주문이 실패하였습니다.');
            }
        })
        .catch(error => {
            // Handle errors
            console.error('Error sending data to the server:', error);
        });

    // Prevent the default form submission
    return false;
}

function sendDiseaseData() {
    // Collect order data from all menu and quantity fields
    var diseaseDate = $("#diseaseDate").val();
    var diseaseStep = $("#diseaseStep").val();
    var diseaseRemark = $("#DiseaseRemark").val();

    var data = {
        diseaseDate: diseaseDate,
        diseaseStep: diseaseStep,
        diseaseRemark: diseaseRemark,
        loginToken: parseInt(document.getElementsByClassName('loginToken').value)
    };
    $.ajax({
        url: "/new-post/disease",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response);
            alert("질병기록이 성공적으로 추가되었습니다.");
            window.location.href = '/get-post/' + response.postDate;
        },
        error: function (error) {
            alert("질병기록 추가에 실패하였습니다.");
            console.log(error);
        }
    })
    return false;
}