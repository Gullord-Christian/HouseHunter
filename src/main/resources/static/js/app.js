const login_btn = document.querySelector("#login-btn");
const register_btn = document.querySelector("#register-btn");
const container = document.querySelector(".container");

if(register_btn){
	register_btn.addEventListener("click", () => {
		container.classList.add("register-mode");
	});
}
if(login_btn){
	login_btn.addEventListener("click", () => {
		container.classList.remove("register-mode");
	});
}
