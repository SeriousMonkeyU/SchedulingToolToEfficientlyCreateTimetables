
var globalId = ""; 
var studentOrTeacher = true;


// setting the current number of week

var today = new Date();
today.setHours(0,0,0,0)
today.setDate(today.getDate() + 3 - (today.getDate() + 6) %7);
var firstWeekOfYear = new Date(today.getFullYear(), 0, 4);
var numberOfWeek = 1 + Math.round(((today.getTime() - firstWeekOfYear.getTime()) / 86400000
- 3 + (firstWeekOfYear.getDay() + 6) % 7) / 7);


function readXML()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200)
        {
            showData(this);
        }
    }
    xhttp.open("GET","xml/Sessions.xml",true);
    xhttp.send();
}


function showData(xml)
{
    var xmlDoc = xml.responseXML;
   
    $(".weekNumber").text(numberOfWeek);

    var sessions = xmlDoc.getElementsByTagName("Session");
    var length = sessions.length;

    resetTable();

    for(let i = 0; i < length; i++)
    {
        let idName;
        if(studentOrTeacher)
        {
             idName = sessions[i].getElementsByTagName("StudentClass")[0].getElementsByTagName("semester")[0].childNodes[0].nodeValue 
        + "" + sessions[i].getElementsByTagName("StudentClass")[0].getElementsByTagName("symbol")[0].childNodes[0].nodeValue;
        }
        else
        {
            idName =  sessions[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
        }
        
        if(sessions[i].getElementsByTagName("weekNumber")[0].childNodes[0].nodeValue == numberOfWeek && globalId == idName)
        {
            let lengthOfSession = sessions[i].getElementsByTagName("TimeSlot")[0].getElementsByTagName("numberOfLessons")[0].childNodes[0].nodeValue;
            let startTime = sessions[i].getElementsByTagName("TimeSlot")[0].getElementsByTagName("startTime")[0].getElementsByTagName("hour")[0].
            childNodes[0].nodeValue;
            let weekDay = sessions[i].getElementsByTagName("weekDay")[0].childNodes[0].nodeValue;
            let course = sessions[i].getElementsByTagName("Course")[0].getElementsByTagName("name")[0].childNodes[0].nodeValue;
            
            let day =  sessions[i].getElementsByTagName("Date")[0].getElementsByTagName("day")[0].childNodes[0].nodeValue;
            let month =  sessions[i].getElementsByTagName("Date")[0].getElementsByTagName("month")[0].childNodes[0].nodeValue;
            let year =  sessions[i].getElementsByTagName("Date")[0].getElementsByTagName("year")[0].childNodes[0].nodeValue;

            let roomName =  sessions[i].getElementsByTagName("Room")[0].getElementsByTagName("name")[0].childNodes[0].nodeValue;
            
            let date = day+"/"+month+"/"+year;
            $("."+weekDay+".date").text(date);

            for(let j = 0; j < lengthOfSession; j++)
            {
                let number = j + parseInt(startTime);
                $("."+number+"."+weekDay).html("<p>"+course+"</p><p>"+roomName+"</p>");
                
            }
        }
    }

    $("td:contains('DMA')").addClass("table-danger");
    $("td:contains('SDJ')").addClass("table-success");
    $("td:contains('RWD')").addClass("table-warning");
    $("td:contains('SEP')").addClass("table-info ");

    
}


function resetTable()
{
    $(".Monday, .Tuesday, .Wednesday, .Thursday, .Friday").each(function () {
        $(this).text("")
        $(this).removeClass("table-danger");
        $(this).removeClass("table-success");
        $(this).removeClass("table-warning");
        $(this).removeClass("table-info");
    })
}



$("#filterStudent").click(function(){
    numberOfWeek = $("#weekNumberStudent").val();
    globalId = $("#studentClass").val();
    studentOrTeacher = true;

    $("#weekNumberStudent").val("");
    $("#studentClass").val("");
    readXML();
})


$("#filterTeacher").click(function(){
    numberOfWeek = $("#weekNumberTeacher").val();
    globalId = $("#teacherInitials").val();

    studentOrTeacher = false;

    $("#weekNumberTeacher").val("");
    $("#teacherInitials").val("");
    readXML();
})

$("#nextWeek").click(function()
{
    if(parseInt(numberOfWeek) < 51)
    {
        numberOfWeek = parseInt(numberOfWeek) + 1;
    }
    else
    {
        numberOfWeek = 1;
    }
    readXML()

})

$("#previousWeek").click(function()
{
    if(parseInt(numberOfWeek) > 1)
    {
        numberOfWeek = parseInt(numberOfWeek) - 1;
    }
    else
    {
        numberOfWeek = 52;
    }    readXML()
})

readXML();
