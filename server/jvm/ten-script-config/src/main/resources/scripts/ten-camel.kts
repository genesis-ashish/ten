camel {
	LOG.info("creating camel route")
    routeHandler {
        from("timer:tick?period=2000")
            .process{ e -> e.getIn().body = "Hello Camel"}
            .to("stream:out")
	LOG.info("inside camel route")
    }
   LOG.info("route ended")
}
