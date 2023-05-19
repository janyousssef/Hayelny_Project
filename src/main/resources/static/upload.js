window.addEventListener("DOMContentLoaded", (event) => {
    const form = document.getElementById("form"); // the form which has input field in it
    const inputFile = document.getElementById("file"); //the input field in the html file 

    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(); //this is initialization of the form array which will contain the uploaded images
        data.append("image", inputFile.files[0]); //this is the file which u uploaded

        fetch("http://20.203.182.15:8080/images", {
            method: "post",
            body: data
        }).then(response => response.json())
            .then((data) => {
                const halLink = data._links.diagnosis.href;
                const halLinkSplit = halLink.split("/");
                const id = halLinkSplit[halLinkSplit.length - 2];
                window.location.href = "http://20.203.182.15:8080/viewresult/" + id;
            })
            .catch((error) => ("Something went wrong!", error));
    };

    form.addEventListener("submit", handleSubmit); //this runs when u submit
});