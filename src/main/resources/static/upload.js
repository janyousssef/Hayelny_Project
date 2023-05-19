window.addEventListener("DOMContentLoaded", (event) => {
    console.log("test")
    const form = document.getElementById("form"); // the form which has input field in it
    const inputFile = document.getElementById("file"); //the input field in the html file 

    const data = new FormData(); //this is initialization of the form array which will contain the uploaded images 
    const boundary = data.boundary;
    const handleSubmit = (event) => {
        event.preventDefault();
        //this for loop gets the multiple files u uploaded 
        for (const file of inputFile.files) {
            data.append("files", file); //add each file to the form array initialized above
            console.log(file);
        }
        image = data[0]
        fetch("http://20.203.182.15:8080/images", {
            method: "post",
            body: image,
            headers: {
                "Content-Type": `multipart/form-data ; boundary=${boundary}`
              }
        }).then(res => {

            console.log(res)
        }).catch((error) => ("Something went wrong!", error));
    };

    form.addEventListener("submit", handleSubmit); //this runs when u submit
});