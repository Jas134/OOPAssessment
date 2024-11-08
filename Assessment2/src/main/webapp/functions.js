function readMore(id, button) {
    const mycontent = document.getElementById(id);
    const mybutton = document.getElementById(button);

    if (mycontent.style.display === 'none' || mycontent.style.display === '') {
        mycontent.style.display = 'block';
        mybutton.textContent = 'Read Less';
    } else {
        mycontent.style.display = 'none';
        mybutton.textContent = 'Read More';
    }
}

