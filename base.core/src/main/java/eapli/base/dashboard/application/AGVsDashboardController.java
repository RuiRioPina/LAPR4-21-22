package eapli.base.dashboard.application;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.AGVState;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.dashboard.domain.*;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.WarehouseInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AGVsDashboardController {

   static private AGVRepository agvRepository = PersistenceContext.repositories().agvs();

    public void showDashboard() {
        HttpsServerAjaxVoting server = new HttpsServerAjaxVoting();
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
        AGVState agvState = null;
        TcpClient tcp = new TcpClient();
        tcp.startConnection("127.0.0.1");
        try {
            agvState = tcp.getAgvState(identity);
            tcp.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
            tcp.stopConnection();
        }
        return agvState;
    }

    public String location(AGV agv) {
        if (agv.agvState().equals(AGVState.FREE)){
            return agv.agvDocks().toString();
        } else {
            return agv.productOrderQueue().get(0).getProductIntegerMap().keySet().iterator().next().getStorageArea().toString();
        }
    }

    public String[][] agvsWarehouse () throws FileNotFoundException {
        WarehouseInfo wi = new WarehouseInfo();
        return wi.warehouseDashboard();
    }
}


