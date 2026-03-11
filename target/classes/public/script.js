// Smooth scrolling cho navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
        }
    });
});

// Xử lý form liên hệ
document.querySelector('.contact-form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const formData = new FormData(this);
    const data = {
        name: formData.get('name'),
        email: formData.get('email'),
        message: formData.get('message')
    };
    
    // Gọi API (có thể mở rộng sau để kết nối backend)
    fetch('/api/contact', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        alert('Cảm ơn bạn đã liên hệ! Chúng tôi sẽ trả lời sớm nhất.');
        document.querySelector('.contact-form').reset();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Có lỗi xảy ra. Vui lòng thử lại.');
    });
});

// Thêm vào giỏ hàng
document.querySelectorAll('.btn-secondary').forEach(button => {
    button.addEventListener('click', function() {
        const productName = this.parentElement.querySelector('h3').textContent;
        alert('Đã thêm "' + productName + '" vào giỏ hàng!');
    });
});

console.log('Flower Shop JavaScript đã tải thành công!');
