function switchQuestion(evt, isNext) {
    evt.preventDefault();
    console.log('aaaa')
}

function ready() {
    let buttons = document.getElementsByClassName('switch-question');
    for (let e of buttons) {

        switch (e.getAttribute('id')) {
            case 'prevq':
                e.addEventListener('click', evt => switchQuestion(evt, false))
                break;
            case 'nextq':
                e.addEventListener('click', evt => switchQuestion(evt, true))
                break;
        }
    }
}

document.addEventListener('DOMContentLoaded', ready);
