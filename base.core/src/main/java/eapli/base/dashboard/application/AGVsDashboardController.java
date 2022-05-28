package eapli.base.dashboard.application;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.AGVState;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.dashboard.domain.*;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class AGVsDashboardController {

   static private AGVRepository agvRepository = PersistenceContext.repositories().agvs();

    public void showDashboard() {
        HttpServerAjaxVoting server = new HttpServerAjaxVoting();
        server.start();
    }

    public List<AGVsDashboardInfoDTO> infoAGVs() throws Exception {
        PersistenceContext.repositories().newTransactionalContext();
        List <AGVsDashboardInfoDTO> agvsDashboardInfoDTOList = new ArrayList<>();
        for (AGV agv : agvRepository.findAll()) {
            AGVState state = getAgvState(agv.identity());
                agvsDashboardInfoDTOList.add(new AGVsDashboardInfoDTO(agv.shortDescription(), state.toString(),
                        location(agv)));
            }
        return agvsDashboardInfoDTOList;
    }

    private AGVState getAgvState(Long identity) throws Exception {
        String [] tcp = new String [2];
        tcp[0] = "127.0.0.1";
        tcp[1] = identity.toString();
        return TcpClient.main(tcp);
    }

    public String location(AGV agv) {
        if (agv.agvState().equals(AGVState.FREE)){
            return agv.agvDocks().toString();
        } else {
            return agv.productOrderQueue().get(0).getProductIntegerMap().keySet().iterator().next().getStorageArea().toString();
        }
    }

}


