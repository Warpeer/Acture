gsap.registerPlugin(ScrollTrigger);

gsap.from('.slogan_content h1', { opacity: 0, duration: 1.2});
gsap.from('.slogan_content p', { opacity: 0, duration: 1.2, delay: 0.5 });
gsap.from('.model', { duration: 1, x: 300, stagger: 0.1, ease: "back.out(1.7)" });

if($(window).width()>645){
    gsap.from('.about_column1', {
        scrollTrigger: '.about_column1',
        opacity: 0,
        duration: 1,
        y: 200,
    });
    gsap.from('.about_column2', {
        scrollTrigger: '.about_column2',
        opacity: 0,
        duration: 0.5,
        delay: 0,
        y: 200,
    });
    
    gsap.from('.service_card', {
        scrollTrigger: {
            trigger: '.service_card'
        },
        opacity: 0,
        duration: 0.5,
        stagger: 0.2,
        x: -1500
    });
    gsap.from('.gallery_wrapper h1', {
        scrollTrigger: '.gallery_wrapper h1',
        opacity: 0,
        duration: 0.5,
    });
    
    gsap.from('#timeline_item_1', {
        scrollTrigger: {
            trigger: "#timeline_item_1",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#timeline_item_2', {
        scrollTrigger: {
            trigger: "#timeline_item_2",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#timeline_item_3', {
        scrollTrigger: {
            trigger: "#timeline_item_3",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#timeline_item_4', {
        scrollTrigger: {
            trigger: "#timeline_item_4",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#dot1', {
        scrollTrigger: {
            trigger: "#timeline_item_1",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#dot2', {
        scrollTrigger: {
            trigger: "#timeline_item_2",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#dot3', {
        scrollTrigger: {
            trigger: "#timeline_item_3",
        },
        duration: 0.5,
        y: 200,
    });
    gsap.from('#dot4', {
        scrollTrigger: {
            trigger: "#timeline_item_4",
        },
        duration: 0.5,
        y: 200,
    });
    let calcTL = gsap.timeline({
        scrollTrigger:{
            trigger: '.calc_wrapper',
        }
    });
    calcTL.from('.calc_img', {
        x: -200,
        duration: 1
    })
    calcTL.from('.calc_text', {
        y: 300, 
        opacity: 0, 
        duration:1
    }, "-=1");
    gsap.from('.contact_illustration',{
        scrollTrigger: '.contact_illustration',
        y:300,
        duration: 0.8
    })
}
