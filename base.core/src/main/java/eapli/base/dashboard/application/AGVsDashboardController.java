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
        List <AGVsDashboardInfoDTO> agvsDashboardInfoDTOList = infoAGVs();
        HttpServerAjaxVoting server = new HttpServerAjaxVoting(agvsDashboardInfoDTOList);
        server.changeController(this);
        server.start();
    }

    public List<AGVsDashboardInfoDTO> infoAGVs(){
        PersistenceContext.repositories().newTransactionalContext();
        List <AGVsDashboardInfoDTO> agvsDashboardInfoDTOList = new ArrayList<>();
        for (AGV agv : agvRepository.findAll()) {
            //System.out.println(location(agv));
                agvsDashboardInfoDTOList.add(new AGVsDashboardInfoDTO(agv.shortDescription(), agv.agvState().toString(),
                        location(agv)));
            }
        return agvsDashboardInfoDTOList;
    }

    public String location(AGV agv) {
        if (agv.agvState().equals(AGVState.FREE)){
            return agv.agvDocks().toString();
        } else {
            return agv.productOrderQueue().get(0).getProductIntegerMap().keySet().iterator().next().getStorageArea().toString();
        }

    }

}


