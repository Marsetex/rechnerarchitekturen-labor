package de.marsetex.pic16f84sim.instruction.byteoriented;

import de.marsetex.pic16f84sim.instruction.IPicInstruction;
import de.marsetex.pic16f84sim.instruction.StatusFlagChangerInstruction;
import de.marsetex.pic16f84sim.microcontroller.PIC16F84;
import de.marsetex.pic16f84sim.microcontroller.memory.DataMemory;
import de.marsetex.pic16f84sim.microcontroller.register.WRegister;

/**
 * Move f. Sets flag: Z
 * Datasheet: Page 64
 */
public class Movf extends StatusFlagChangerInstruction {

    private final byte fileRegister;
    private final short destination;

    public Movf(short opcode) {
        fileRegister = (byte) (opcode & 0x007F);
        destination = (short) (opcode & 0x0080);
    }

    @Override
    public void execute(PIC16F84 pic) {
        DataMemory dataMemory = pic.getDataMemory();

        byte value = dataMemory.load(fileRegister);
        isValueEqualsZero(value);

        if(destination == 0) {
            pic.getWRegister().setWRegisterValue(value);

        } else {
            dataMemory.store(fileRegister, value);
        }
    }
}
