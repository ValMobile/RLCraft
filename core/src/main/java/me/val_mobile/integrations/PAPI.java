/*
    Copyright (C) 2023  Val_Mobile

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package me.val_mobile.integrations;

import me.val_mobile.realisticsurvival.RealisticSurvivalPlugin;

import javax.annotation.Nonnull;

public class PAPI extends CompatiblePlugin {

    public static final String NAME = "PlaceholderAPI";

    public PAPI(@Nonnull RealisticSurvivalPlugin plugin) {
        super(plugin, NAME);

        if (isIntegrated) {
            new TemperatureThirstBarExpansion().register();
            new TemperatureBarExpansion().register();
            new TemperatureNumericExpansion().register();
            new TemperatureVignetteExpansion().register();
            new ThirstBarExpansion().register();
            new ThirstNumericExpansion().register();
            new ThirstVignetteExpansion().register();
        }
    }

    @Override
    public boolean otherLoadOptions() {
        return true;
    }
}
