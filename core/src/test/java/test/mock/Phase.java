package test.mock;

/**
 * The phase of mocking.
 */
enum Phase {

    /**
     * In this phase, the next invocation will define what is returned.
     */
    returns,

    /**
     * In this phase, invocations will behave according to specification.
     * This is the only phase that should be used by the object being tested.
     */
    invoke,

    /**
     * In this phase, the next invocation will verify previous invocation happened.
     */
    verify,

    /**
     * In this phase, the next invocation will forbid invocations after it.
     */
    no;


    /**
     * The current phase.
     * This is essentially a global variable.
     * On the bright side, this whole class is package private.
     */
    static Phase current;

}
