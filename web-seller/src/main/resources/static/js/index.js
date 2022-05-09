const arrayBtnMenu = document.querySelectorAll('.js-menu-toggle');
arrayBtnMenu.forEach(element => {
	element.addEventListener('click', function (e) {
		e.preventDefault();
		const temp = document.querySelector('body');
		if (temp.classList.contains("offcanvas-menu")) {
			temp.classList.remove('offcanvas-menu');
		}
		else {
			temp.classList.add('offcanvas-menu');
		}
	})
});

document.addEventListener('mouseup', function (e) {
	const container = document.querySelector(".site-mobile-menu");
	if (!container.contains(e.target)) {
		if (document.querySelector('body').classList.contains('offcanvas-menu')) {
			document.querySelector('body').classList.remove('offcanvas-menu');
		}
	}
});

window.addEventListener('resize', function () {
	const w = window.innerWidth;
	if (w > 768) {
		const temp = document.querySelector('body');
		if (temp.classList.contains('offcanvas-menu')) {
			temp.classList.remove('offcanvas-menu');
		}
	}
});

const collapse = document.querySelector('.arrow-collapse');
collapse.addEventListener('click', function (e) {
	const temp = document.querySelector('.collapse');
	if (temp.classList.contains('show')) {
		temp.classList.remove('show');
	} else {
		temp.classList.add('show');
	}
})


$('.owl-carousel').owlCarousel({
	loop: true,
	margin: 20,
	nav: true,
	responsiveClass: true,
	responsive: {
		0: {
			items: 1
		},
		576: {
			items: 2,
			nav: false
		},
		768: {
			items: 3,
			nav: true
		},
		992: {
			items: 4,
			nav: true
		},
		1200: {
			items: 5,
			nav: true
		}
	}
})
