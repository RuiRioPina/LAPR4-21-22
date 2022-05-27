package eapli.base.dashboard.domain;

import eapli.base.dashboard.application.AGVsDashboardController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */

public class HttpServerAjaxVoting extends Thread {
    private final static AuthorizationService authz = AuthzRegistry.authorizationService();
    private final static String username = authz.session().get().authenticatedUser().username().toString();
    private final static String email = authz.session().get().authenticatedUser().email().toString();

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";
    static private ServerSocket sock;
    static private List<AGVsDashboardInfoDTO> agvsDashboard;
    static String PORT = "8081";
    private static AGVsDashboardController controller;

    public HttpServerAjaxVoting() {
    }

    @Override
    public void run () {
	Socket cliSock = null;

	try {
        sock = new ServerSocket(Integer.parseInt(PORT));
    }
	catch (IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
    }

	    while (true) {
            try {
            cliSock=sock.accept();
            } catch (IOException e) {
            e.printStackTrace();
        }

        HttpAjaxVotingRequest req = new HttpAjaxVotingRequest(cliSock, BASE_FOLDER);
        req.start();
        incAccessesCounter();
        }
    }
	
    
    // DATA ACCESSED BY THREADS - LOCKING REQUIRED

    private static final int candidatesNumber = 4;
    private static final String[] candidateName = new String[candidatesNumber];
    private static final int[] candidateVotes = new int[candidatesNumber];
    private static int accessesCounter;
    
    private static synchronized void incAccessesCounter() { accessesCounter++; }
    
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

