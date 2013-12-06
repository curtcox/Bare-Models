package test.mock;

/**
 * The phase of mocking.
 */
enum Phase {

    when,

    invoke,

    invoke_or_when,

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
     * Either "verify", "no", or null.
     */
    static Phase current;

}
