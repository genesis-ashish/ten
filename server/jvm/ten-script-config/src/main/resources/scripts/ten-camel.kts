camel {
    LOG.info("creating camel route")
    routeHandler {
        restConfiguration().host("www.boredapi.com").port(80)
        from("timer:tick?period=2000")
            .to("rest:get:/api/activity")
            .to("stream:out")
        LOG.info("inside camel route")
    }
    LOG.info("route ended")
}

