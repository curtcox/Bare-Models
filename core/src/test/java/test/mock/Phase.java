package test.mock;

/**
 * The phase of mocking.
 */
enum Phase {

    when,

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
     * Either "when", "verify", "no", or "invoke".
     */
    static Phase current;

}
