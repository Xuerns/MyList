document.querySelector(".container-myday").classList.add("on");

// Open Sidebar
function openSidebar() {
  const listside = document.querySelectorAll(".to-do-list-sidebar")
  const openButton = document.querySelector(".sidebar");
  const appear = document.querySelectorAll(".appear");
  const appeartheme = document.querySelector(".appear-theme");
  const onContainer = document.querySelectorAll(".container-list");
  const onThisButton = document.querySelectorAll(".onThis");

  appeartheme.classList.toggle("non");
  
  openButton.classList.toggle("open");
  listside.forEach((side) => {
    side.classList.toggle("open");
  })
  onContainer.forEach((container) => {
    container.classList.toggle("open");
  });
  appear.forEach((item) => {
    item.classList.toggle("non");
  });
  onThisButton.forEach((item) => {
    item.classList.toggle("open");
  });
}

// Change Theme
function changeTheme() {
  const Tcontainer = document.querySelector(".theme-container");
  const Tbutton = document.querySelector(".theme-button");
  const Tsidebar = document.querySelector(".sidebar");
  const Tlist = document.querySelectorAll(".list-todo");
  const Tinput = document.querySelectorAll(".input-list");
  const Tboxdetails = document.querySelectorAll(".box-details");

  Tcontainer.classList.toggle("dark");
  Tbutton.classList.toggle("dark");
  Tsidebar.classList.toggle("dark");

  Tinput.forEach((ListBar) => {
    ListBar.classList.toggle("dark");
  });
  Tlist.forEach((ListBar) => {
    ListBar.classList.toggle("dark");
  });
  Tboxdetails.forEach((item) => {
    item.classList.toggle("dark");
  });
}

// Date
const options = {
  weekday: "long",
  year: "numeric",
  month: "long",
  day: "numeric",
};
const tanggal = new Date();
const currentDate = tanggal.toLocaleDateString("id-ID", options);
const Alldatelist = document.querySelectorAll(".date-info");
Alldatelist.forEach((dateList) => {
  dateList.innerText = currentDate;
});

// Trigger //
const inputBox = document.querySelector(".input-todo-list");
const todoListContainer = document.querySelectorAll(".todo-list");
const inputimpbox = document.querySelector(".input-imp-list");
const impListContainer = document.querySelectorAll(".imp-list");
const inputplanbox = document.querySelector(".input-planned-list");
const planListContainer = document.querySelectorAll(".planned-list");
const inputProjectList = document.querySelector(".input-project-list");
const projectListContainer = document.querySelectorAll(".project-list");
const inputTaskBar = document.querySelector(".input-task-list");
const taskListMain = document.querySelector(".task-list");

document.addEventListener("keydown", function (event) {
  if (event.key !== "Enter") {
    return;
  }
  const Inputtarget = event.target;
  const taskTesk = Inputtarget.value.trim();
  if (taskTesk === "") {
    return;
  }
  if (Inputtarget === inputBox) {
    createNewList(taskTesk);
  } else if (Inputtarget === inputimpbox) {
    createNewListImp(taskTesk);
  } else if (Inputtarget === inputplanbox) {
    createNewListPlan(taskTesk);
  } else if (Inputtarget === inputProjectList) {
    createNewListProj(taskTesk);
  } else if (Inputtarget === inputTaskBar) {
    createNewTask(taskTesk);
  }
  Inputtarget.value = "";
});

// Create New List My day //
function createNewList(text) {
  todoListContainer.forEach((container) => {
    const newLi = document.createElement("li");
    const newCheckbox = document.createElement("input");
    const newSpan = document.createElement("span");

    newLi.classList.add("main-list", "list-todo");
    newCheckbox.type = "checkbox";
    newCheckbox.classList.add("submit-list");
    newSpan.textContent = text;

    // Check for Theme //
    const sidebar = document.querySelector(".sidebar");
    if (sidebar.classList.contains("dark")) {
      newLi.classList.add("dark");
    }

    newLi.appendChild(newCheckbox);
    newLi.appendChild(newSpan);
    container.appendChild(newLi);
  });

  console.log("Add New Task");
}

// Create New List Important //
function createNewListImp(text) {
  impListContainer.forEach((container) => {
    const newLi = document.createElement("li");
    const newCheckbox = document.createElement("input");
    const newSpan = document.createElement("span");

    newLi.classList.add("imp-main-list", "list-todo");
    newCheckbox.type = "checkbox";
    newCheckbox.classList.add("submit-list");
    newSpan.textContent = text;

    const sidebar = document.querySelector(".sidebar");
    if (sidebar.classList.contains("dark")) {
      newLi.classList.add("dark");
    }

    newLi.appendChild(newCheckbox);
    newLi.appendChild(newSpan);
    container.appendChild(newLi);
  });
  console.log("Add New Important Task");
}

// Create New List Planned //
function createNewListPlan(text) {
  planListContainer.forEach((container) => {
    const newLi = document.createElement("li");
    const newCheckbox = document.createElement("input");
    const newSpan = document.createElement("span");

    newLi.classList.add("planned-main-list", "list-todo");
    newCheckbox.type = "checkbox";
    newCheckbox.classList.add("submit-list");

    newSpan.textContent = text;

    const sidebar = document.querySelector(".sidebar");
    if (sidebar.classList.contains("dark")) {
      newLi.classList.add("dark");
    }
    newLi.appendChild(newCheckbox);
    newLi.appendChild(newSpan);
    container.appendChild(newLi);
  });
  console.log("Add New Plan");
}

// Create New List Project //
function createNewListProj(text) {
  projectListContainer.forEach((container) => {
    const newLi = document.createElement("li");
    const newSpan = document.createElement("span");
    const newCheckbox = document.createElement("input");

    newLi.classList.add("project-main-list", "list-todo");
    newCheckbox.type = "checkbox";

    newCheckbox.classList.add("submit-list");
    newSpan.textContent = text;

    const sidebar = document.querySelector(".sidebar");
    if (sidebar.classList.contains("dark")) {
      newLi.classList.add("dark");
    }
    newLi.appendChild(newCheckbox);
    newLi.appendChild(newSpan);
    container.appendChild(newLi);
  });

  console.log("Add New Project");
}

// Create New Task List //
function createNewTask(text) {
  const newLi = document.createElement("li");
  const newCheckbox = document.createElement("input");
  const newSpan = document.createElement("span");

  newLi.classList.add("task-main-list", "list-todo");
  newCheckbox.classList.add("submit-list");

  newCheckbox.type = "checkbox";
  newSpan.textContent = text;

  const sidebar = document.querySelector(".sidebar");
  if (sidebar.classList.contains("dark")) {
    newLi.classList.add("dark");
  }

  newLi.appendChild(newCheckbox);
  newLi.appendChild(newSpan);
  taskListMain.appendChild(newLi);
  console.log("Add New Task");
}


// Declaration for Appear
const OffList = document.querySelectorAll(".container-list");

// Sidebar Content Myday //
function appearMyday() {
  const Onmyday = document.querySelector(".container-myday");

  OffList.forEach((container) => {
    container.classList.remove("on");
  });
  Onmyday.classList.add("on");
  document.body.style.backgroundImage =
    "url(/To-Do-List/Picture/Background-My-Day.png)";
}

// Sidebar Content Important //
function appearImp() {
  const OnImp = document.querySelector(".container-important");

  OffList.forEach((container) => {
    container.classList.remove("on");
  });
  OnImp.classList.add("on");
  document.body.style.backgroundImage =
    "url(/To-Do-List/Picture/Background-Important.png)";
}

// Sidebar Content Planned //
function appearPlan() {
  const OnPlan = document.querySelector(".container-planned");

  OffList.forEach((container) => {
    container.classList.remove("on");
  });
  OnPlan.classList.add("on");
  document.body.style.backgroundImage =
    "url(/To-Do-List/Picture/Background.png)";
}

// sidebar Content Project //
function appearProject() {
  const OnProject = document.querySelector(".container-project");

  OffList.forEach((container) => {
    container.classList.remove("on");
  });
  OnProject.classList.add("on");
  document.body.style.backgroundImage =
    "url(/To-Do-List/Picture/Background-Project.png)";
}

// Sidebar Content Task //
function appearTask() {
  const OnTask = document.querySelector(".container-task");

  OffList.forEach((container) => {
    container.classList.remove("on");
  });
  OnTask.classList.add("on");
  document.body.style.backgroundImage =
    "url(/To-Do-List/Picture/Background-Task.png)";
}
