package me.oyurimatheus.nossoservicodepagamento.payment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
class FraudCheckService {

    private final Set<FraudCheck> fraudChecks;

    FraudCheckService(Set<FraudCheck> fraudChecks) {
        this.fraudChecks = fraudChecks;
    }

    List<Fraud> checkClient(String email) {

        return fraudChecks.stream()
                           .map(checking -> checking.check(email))
                           .filter(Optional::isPresent)
                           .map(Optional::get)
                           .collect(toList());
    }
}
