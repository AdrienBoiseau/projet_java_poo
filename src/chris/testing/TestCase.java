package chris.testing;

class TestCase<I, O> {
    private final I input;
    private final O output;

    TestCase(I i, O o) {
        this.input = i;
        this.output = o;
    }

    O getOutput() {
        return output;
    }

    I getInput() {
        return input;
    }
}
