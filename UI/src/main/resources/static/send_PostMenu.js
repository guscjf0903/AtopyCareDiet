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
    fetch('/menu/new', {
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
                    window.location.href = '/menu/'+data.postId;
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