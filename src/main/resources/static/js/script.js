document.addEventListener("DOMContentLoaded", function() {

    var forms = document.querySelectorAll("form");
    forms.forEach(function(form) {
        form.addEventListener("submit", function(event) {
            var valid = true;
            var inputs = form.querySelectorAll("input");

            inputs.forEach(function(input) {
                var errorMessage = input.nextElementSibling;

                if (input.hasAttribute("required") && input.value.trim() === "") {
                    valid = false;
                    input.classList.add("invalid");
                    errorMessage.textContent = "このフィールドは必須です。";
                } else {
                    input.classList.remove("invalid");
                    errorMessage.textContent = "";
                }

                if (input.type === "number") {
                    var min = input.getAttribute("min");
                    var max = input.getAttribute("max");
                    var value = parseFloat(input.value);

                    if (min !== null && value < min) {
                        valid = false;
                        input.classList.add("invalid");
                        errorMessage.textContent = "値は " + min + " 以上でなければなりません。";
                    }

                    if (max !== null && value > max) {
                        valid = false;
                        input.classList.add("invalid");
                        errorMessage.textContent = "値は " + max + " 以下でなければなりません。";
                    }
                }
            });

            if (!valid) {
                event.preventDefault();
            }
        });
    });

    var deleteLinks = document.querySelectorAll("a.delete");
    deleteLinks.forEach(function(link) {
        link.addEventListener("click", function(event) {
            if (!confirm("このアイテムを削除してもよろしいですか？")) {
                event.preventDefault();
            }
        });
    });
});
