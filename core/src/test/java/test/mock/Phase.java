package test.mock;

/**
 * The phase of mocking.
 */
enum Phase {

    /**
     * The "verify" phase.
     * MockFactory invocations should verify that previous invocations happened.
     */
    verify,

    /**
     * The "no" phase.
     * MockFactory invocations should verify that previous invocations happened.
     */
    no,

    /**
     * Neither "verify", nor "no".
     * The mocks are either being used to specify mock behaviour (whens),
     * or to execute the code under test.  Unfortunately, there is no way to tell
     * which it is before-the-fact, so this mode needs to support both.
     */
    other;

    /**
     * The current phase.
     */
    static Phase current;

}
