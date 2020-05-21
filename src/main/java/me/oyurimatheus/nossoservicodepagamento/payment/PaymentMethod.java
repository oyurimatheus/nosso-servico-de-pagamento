package me.oyurimatheus.nossoservicodepagamento.payment;

enum PaymentMethod {

    CREDIT_CARD {

        @Override
        String description() {
            return "A credit card used to pay your purchases online or in local";
        }

        @Override
        boolean payOnline() {
            return true;
        }
    },
    CASH {

        @Override
        String description() {
            return "The good old money";
        }

        @Override
        boolean payOnline() {
            return false;
        }
    },
    CARD_MACHINE {

        @Override
        String description() {
            return "Pay with all your cards";
        }

        @Override
        boolean payOnline() {
            return false;
        }
    },
    CHECK {

        @Override
        String description() {
            return "A way to pay where places do not accept cards";
        }

        @Override
        boolean payOnline() {
            return false;
        }
    };


    /**
     *
     * @return a description of payment method chose
     */
    abstract String description();

    /**
     *
     * @return if payment method is able to be used online
     */
    abstract boolean payOnline();
}
