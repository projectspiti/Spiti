package com.dietapp.service;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.model.SupportIssueType;
import com.dietapp.service.support.handler.DeliverySupportHandler;
import com.dietapp.service.support.handler.GeneralSupportHandler;
import com.dietapp.service.support.handler.KitchenSupportHandler;
import com.dietapp.service.support.handler.PaymentSupportHandler;
import com.dietapp.service.support.handler.RefundSupportHandler;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SupportComplaintResolverServiceTest {

    @Test
    void routesDeliveryIssueToDeliverySupportHandler() {
        SupportComplaintResolverService service = resolverService();

        SupportComplaintResponse response = service.resolve(new SupportComplaintRequest(
                "ORDER-101",
                SupportIssueType.DELIVERY_DELAY,
                "Lunch delivery is late"
        ));

        assertThat(response.handledBy()).isEqualTo("DeliverySupportHandler");
        assertThat(response.resolutionMessage()).contains("revised ETA");
    }

    @Test
    void routesGeneralHelpToFallbackHandler() {
        SupportComplaintResolverService service = resolverService();

        SupportComplaintResponse response = service.resolve(new SupportComplaintRequest(
                "ORDER-102",
                SupportIssueType.GENERAL_HELP,
                "Need help with my diet subscription"
        ));

        assertThat(response.handledBy()).isEqualTo("GeneralSupportHandler");
    }

    private SupportComplaintResolverService resolverService() {
        return new SupportComplaintResolverService(List.of(
                new GeneralSupportHandler(),
                new RefundSupportHandler(),
                new DeliverySupportHandler(),
                new KitchenSupportHandler(),
                new PaymentSupportHandler()
        ));
    }
}
