# US2005
=======================================


# 1. Requisitos

**As Warehouse Employee, I want to open a web dashboard presenting the current status of the AGVs as well as their position in the warehouse layout and keeps updated automatically (e.g.: at each minute).**

* It must be used the provided application protocol (SPOMS2022). The dashboard is intended to be displayed on a web page provided by an existing HTTP server in the "BackOfficeApp" application and only available to localhost. The dashboard web page is kept updated without reloading.

# 2. Análise

É necessário obter a informação dos AGVs (description,status e posição) usando o protocolo fornecido.

A Dashboard tem de se manter atualizada sem dar reload.

# 2. Implementação

Para receber a informação disponibilizada pelo BackOfficeApp server esta funcionalidade utiliza sockets.

Assim, é feito um request ao referido servidor sempre que é necessária uma atualização.

Para concretizar a atualização o servidor HTML ,implementado em java, depois de receber os dados, manipula as Strings que recebe de modo a representar a informação de forma correta na dashboard.

Uma função em JavaScript assegura que a informação é atualizada.

# 3. Integração/Demonstração

Para o servidor HTTP utilizamos a porta x em localhost.

```
function refreshAGVsDashboardInfo() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("agvsDashboard");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshAGVsDashboardInfo, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshAGVsDashboardInfo, 2000);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshAGVsDashboardInfo, 2000);
    };

    request.open("GET", "/agvsDashboard", true);
    request.timeout = 2000;
    request.send();
}

function refreshPersonalInfo() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("personalInfo");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPersonalInfo, 2000);
    };

    request.open("GET", "/personalInfo", true);
    request.timeout = 2000;
    request.send();
}

public static synchronized String getPersonalInfo () {
        return " <div class=\"topnav\" id=\"personalInformation\">\n" +
                "    <a class=\"active\" href=\"#home\">Personal Information</a>\n" +
                "    <a href=> Name: " + username + "</a>\n" +
                "    <a href=> Email: " + email + "</a>\n" +
                "</div> ";
    }

    public static synchronized String refreshAGVsDashboardInfo() {
        try {
            AGVsDashboardController controller = new AGVsDashboardController();
            agvsDashboard = controller.infoAGVs();
            if (agvsDashboard != null) {
                StringBuilder s = new StringBuilder();
                for (AGVsDashboardInfoDTO agv : agvsDashboard) {
                    s.append("<tr class=\"active-row\">" +
                            "<td>" + agv.AGVDescription + "</td>" +
                            "<td>" + agv.AGVStatus + "</td>" +
                            "<td>" + agv.AGVPosition + "</td>" +
                            "</tr>");
                }
                return s.toString();
            } else {
                return " ";
            }
        } catch (NullPointerException ne) {
            return " ";
        }
    }
```

# 4. Observações




