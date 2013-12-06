package test.mock;

/**
 * The phase of mocking.
 */
enum Phase {

    returns,

    invoke,

    /**
     * The "verify" phase.
     * MockFactory invocations should verify that previous invocations happened.
     */
    verify,

    /**
     * The "no" phase.
     * MockFactory invocations should verify that previous invocations happened.
     */
    no;


    /**
     * The current phase.
     * Either "returns", "verify", "no", or "invoke".
     */
    static Phase current;

}
