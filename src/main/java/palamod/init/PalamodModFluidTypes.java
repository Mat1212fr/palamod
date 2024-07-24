
/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package palamod.init;

import palamod.fluid.types.FakewterFluidType;
import palamod.fluid.types.AngelicwterFluidType;

import palamod.PalamodMod;

public class PalamodModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, PalamodMod.MODID);
	public static final RegistryObject<FluidType> FAKEWTER_TYPE = REGISTRY.register("fakewter", () -> new FakewterFluidType());
	public static final RegistryObject<FluidType> ANGELICWTER_TYPE = REGISTRY.register("angelicwter", () -> new AngelicwterFluidType());
}
