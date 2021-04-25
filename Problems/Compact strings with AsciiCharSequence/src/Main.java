
class AsciiCharSequence implements CharSequence {

    byte[] input;

    AsciiCharSequence(byte[] input) {
        this.input = input.clone();
    }

    public int length() {
        return input.length;
    }

    public char charAt(int charIndex) {
        return (char) input[charIndex];
    }

    public AsciiCharSequence subSequence(int startIndex, int endIndex) {
        /*byte[] output = new byte[endIndex - startIndex];
        for (int i = startIndex, j = 0; i < endIndex; i++, j++) {
            output[j] = input[i];
        }

        return new AsciiCharSequence(output);*/
        return new AsciiCharSequence(Arrays.copyOfRange(input, startIndex, endIndex));
    }

    public String toString() {
        return new String(input);
    }

}
